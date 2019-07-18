package com.netzme.test.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;


@Component("dbPropBean")
public class DbPropBean {

    @Autowired private ConfigurableEnvironment env;

    @Autowired private NamedParameterJdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private void init(){
        MutablePropertySources sources = env.getPropertySources();

        Properties dbProp = new Properties();

        PropertySource<Map<String, Object>> newProp = new PropertiesPropertySource("dbProp", dbProp);
        setProp(dbProp);

        sources.addLast(newProp);

    }

    private void destroying(){
        logger.info("Destroying DbPropBean..");
    }

    private void setProp(Properties prop){
        StringJoiner sjQuery = new StringJoiner("");
        sjQuery.add("select PARAM_DSC key, PARAM_CHAR_VAL val from APPLICATION_PARAMETERS params where status = '1' ");

        List<PropKeyValue> resultList = jdbcTemplate.query(sjQuery.toString(),
                BeanPropertyRowMapper.newInstance(PropKeyValue.class));
        for(PropKeyValue propKV : resultList){
            prop.setProperty(propKV.getKey(), propKV.getValue());
        }
    }
}
