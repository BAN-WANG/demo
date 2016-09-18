package com.demo.base.mybatis;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 在构建SQL时进行拦截，修改SQL语言进行分页
 * 分页判断：根据方法参数中是否包含有Page对象
 */
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();

        BoundSql boundSql = handler.getBoundSql();

        Object parameterObject = boundSql.getParameterObject();

        Page page = getPageParam(parameterObject);//获取参数中Page对象
        if (page != null) {//若有存在Page对象则进行相关分页操作：总记录数+总页数+数据
            String sql = boundSql.getSql();
            ParameterHandler parameterHandler = handler.getParameterHandler();

            Connection connection = (Connection) invocation.getArgs()[0];

            //1.查询总记录
            page.setTotal(qryTotalSize(sql,parameterHandler,connection));

            //2.查询分页数据
            //获取正确当前页数（如果当前页大于总页数则取总页数）
            int pageNo = page.getPageNo()>page.getTotalPage() ? page.getTotalPage() : page.getPageNo();
            if(pageNo < 1)  pageNo = 1;
            page.setPageNo(pageNo);

            int startPos = 0;
            if(pageNo > 1){
                startPos = (pageNo - 1) * page.getPageSize();
            }
            String pageSql = sql + " limit " + startPos + "," + page.getPageSize();
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);//使用java反射重新SQL值（带分页）
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private int qryTotalSize(String sql,ParameterHandler parameterHandler, Connection connection) throws Throwable{
        String countSql = "select count(1) from ( " + sql + " ) t";

        PreparedStatement pstmt = connection.prepareStatement(countSql);//设置执行SQL
        parameterHandler.setParameters(pstmt);//设置参数值
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return  rs.getInt(1);
        }
        return 0;
    }

    private Page getPageParam(Object obj) {
        if (obj instanceof Page) {
            return (Page) obj;
        } else if (obj instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) obj;
            Set<Map.Entry> entrySet = paramMap.entrySet();
            for (Map.Entry entry : entrySet) {
                if (entry.getValue() instanceof Page) {
                    return (Page) entry.getValue();
                }
            }
        }
        return null;
    }

    private static class ReflectUtil {
        /**
         * 利用反射获取指定对象的指定属性
         *
         * @param obj       目标对象
         * @param fieldName 目标属性
         * @return 目标属性的值
         */
        public static Object getFieldValue(Object obj, String fieldName) {
            Object result = null;
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    result = field.get(obj);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return result;
        }

        /**
         * 利用反射获取指定对象里面的指定属性
         *
         * @param obj       目标对象
         * @param fieldName 目标属性
         * @return 目标字段
         */
        private static Field getField(Object obj, String fieldName) {
            Field field = null;
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException e) {
                    //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
                }
            }
            return field;
        }

        /**
         * 利用反射设置指定对象的指定属性为指定的值
         *
         * @param obj        目标对象
         * @param fieldName  目标属性
         * @param fieldValue 目标值
         */
        public static void setFieldValue(Object obj, String fieldName,
                                         Object fieldValue) {
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(obj, fieldValue);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
