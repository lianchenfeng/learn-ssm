package com.kaishengit.dao.impl;

import com.kaishengit.pojo.Node;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class NodeDao {

    @Inject
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Node node) {
        String sql = "insert into t_node(nodename) values(:name)";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", node.getNodename());

        namedParameterJdbcTemplate.update(sql, param);
    }

    public void update(Node node) {
        String sql = "update t_node set nodename=:name where id = :id";

       /* Map<String,Object> param = new HashMap<String, Object>();
        param.put("id",node.getId());
        param.put("name",node.getNodename());*/

        /*SqlParameterSource ParameterSource = new BeanPropertySqlParameterSource(node);*/

        SqlParameterSource parameterSource = new MapSqlParameterSource()

                .addValue("name", node.getNodename())
                .addValue("id", node.getId());

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public Node findById(Integer id) {
        String sql = "select * from t_node where id = :id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, param, new BeanPropertyRowMapper<Node>(Node.class));

    }

    public List<Node> fingAll() {

        String sql = "select * from t_node";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Node>(Node.class));
    }



    /*@Inject
    private JdbcTemplate jdbcTemplate;

    public void save(Node node){
        String sql = "insert into t_node(nodename) value(?)";
        jdbcTemplate.update(sql,node.getNodename());
    }

    public Node findById(Integer id){
        String sql = "select * from t_node where id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Node>(Node.class),id);
    }

    public List<Node> fingAll(){
        String sql = "select * from t_node";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Node>(Node.class));
    }*/
}
