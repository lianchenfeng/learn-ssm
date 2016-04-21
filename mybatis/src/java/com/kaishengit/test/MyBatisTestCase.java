package com.kaishengit.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kaishengit.mapper.NodeMapper;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.mapper.TopicMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Node;
import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;


public class MyBatisTestCase {

    private SqlSessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void testBuilderSQLSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        Assert.assertNotNull(sessionFactory);
    }

    @Test
    public void testSave() {
        SqlSession session = sessionFactory.openSession();

        Student student = new Student();
        student.setStuname("MyBatis3");
        student.setStuage(3);

        session.insert("com.kaishengit.mapper.StudentMapper.save", student);

        session.commit(); //!!! 事务要提交或回滚
        session.close();
    }

    @Test
    public void testUpdate() {
        SqlSession session = sessionFactory.openSession();

        Student student = new Student();
        student.setId(28);
        student.setStuname("Java");
        student.setStuage(23);

        session.update("com.kaishengit.mapper.StudentMapper.update", student);

        session.commit();
        session.close();
    }

    @Test
    public void testDel() {
        SqlSession session = sessionFactory.openSession();
        session.delete("com.kaishengit.mapper.StudentMapper.del", 28);
        session.commit();
        session.close();
    }

    @Test
    public void testFindById() {
        SqlSession session = sessionFactory.openSession();

        Student student = session.selectOne("com.kaishengit.mapper.StudentMapper.findById", 29);
        System.out.println(student.getStuname() + "\t" + student.getStuage());

        session.commit();
        session.close();
        Assert.assertNotNull(student);
    }

    @Test
    public void testFindAll() {
        SqlSession session = sessionFactory.openSession();

        List<Student> studentList = session.selectList("com.kaishengit.mapper.StudentMapper.findAll");

        for (Student stu : studentList) {
            System.out.println(stu.getStuname() + " \t " + stu.getStuage());
        }

        session.commit();
        session.close();

        Assert.assertNotNull(studentList);
        Assert.assertEquals(studentList.size(), 4);

    }

    @Test
    public void testSaveWithInterface() {
        SqlSession session = sessionFactory.openSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setStuname("李四");
        student.setStuage(26);

        studentMapper.save(student);

        session.commit();
        session.close();

    }

    @Test
    public void testUpdateWithInterface() {
        SqlSession session = sessionFactory.openSession();

        StudentMapper mapper = session.getMapper(StudentMapper.class);
        Student student = mapper.findById(29);

        student.setStuage(30);
        mapper.update(student);

        session.commit();
        session.close();

    }

    @Test
    public void testFindAllWithInterface() {
        SqlSession session = sessionFactory.openSession();

        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> studentlist = mapper.findAll();

        for (Student stu : studentlist) {
            System.out.println(stu.getStuname() + " \t " + stu.getStuage());
        }

        session.commit();
        session.close();

        Assert.assertNotNull(studentlist);
        Assert.assertEquals(studentlist.size(), 8);

    }

    @Test
    public void testTopicFindById() {
        SqlSession session = sessionFactory.openSession();

        TopicMapper mapper = session.getMapper(TopicMapper.class);
        Topic topic = mapper.findById(1);

        System.out.println(topic.getTitle());
        System.out.println("------------------");
        System.out.println(topic.getUser().getUsername());
        System.out.println(topic.getNode().getNodename());

        session.commit();
        session.close();

    }

    @Test
    public void testNodeFindById() {
        SqlSession session = sessionFactory.openSession();

        NodeMapper nodeMapper = session.getMapper(NodeMapper.class);
        Node node = nodeMapper.findById(1);

        System.out.println(node.getNodename());
        System.out.println("-----------------------");
        List<Topic> topics = node.getTopicList();

        for (Topic topic : topics) {
            System.out.println(topic.getTitle());
        }

        session.commit();
        session.close();

    }

    @Test
    public void testFindUser() {
        SqlSession session = sessionFactory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findByUserNameAndPassword("tom", "56481aecfd58b9882bd463a609b97cf7");

        session.commit();
        session.close();

        Assert.assertNotNull(user);
    }

    @Test
    public void testFindMap() {
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = userMapper.findMapById(10);
        System.out.println(map.get("username"));
        System.out.println(map.get("avatar"));

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testGenerateKey() {
        SqlSession sqlSession = sessionFactory.openSession();

        Node node = new Node();
        node.setNodename("框架");

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);
        nodeMapper.save(node);

        sqlSession.commit();
        sqlSession.close();

        System.out.println("ID:" + node.getId());
        Assert.assertNotNull(node.getId());
    }

    @Test
    public void testSavaWithMap() {
        SqlSession sqlSession = sessionFactory.openSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        Map<String, Object> param = Maps.newHashMap();
        param.put("id", null);
        param.put("nodename", "互联网");

        nodeMapper.saveWithMap(param);

        System.out.println(param.get("id"));

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testBuildSql() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("type", "xxx");
        params.put("pinpai", "xxx");
        params.put("size", "xxx");

        String sql = SQLUtil.buildSql(params);

        System.out.println(sql);

    }

    @Test
    public void testFingTopicByParam() {
        SqlSession sqlSession = sessionFactory.openSession();

        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

        Map<String, Object> param = Maps.newHashMap();
        param.put("title", "j");
        //param.put("text","java");
        //param.put("userid",11);
        //param.put("nodeid",1);


        List<Topic> topics = topicMapper.findByparam(param);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testFindTopicByNodeIds() {
        SqlSession sqlSession = sessionFactory.openSession();

        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

        List<Integer> nodeIds = Lists.newArrayList(1,2,3,4);
        List<Topic> topics = topicMapper.findByNodeIds(nodeIds);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testLevelOneCache(){

        SqlSession sqlSession = sessionFactory.openSession();

        NodeMapper mapper = sqlSession.getMapper(NodeMapper.class);

        Node node = mapper.findById(1);
        Node node2 = mapper.findById(1);

        sqlSession.commit();
        sqlSession.close();

        Assert.assertNotNull(node2);
    }

    @Test
    public void testLevelSecondCache(){

        SqlSession sqlSession = sessionFactory.openSession();

        NodeMapper mapper = sqlSession.getMapper(NodeMapper.class);

        Node node = mapper.findById(1);

        sqlSession.commit();
        sqlSession.close();

        //----------------------------------------------------

        SqlSession session2 = sessionFactory.openSession();
        mapper = session2.getMapper(NodeMapper.class);

        Node node2 = mapper.findById(1);

        session2.commit();
        session2.close();

        Assert.assertNotNull(node2);
    }

    @Test
    public void testFindTopicById(){

        SqlSession session = sessionFactory.openSession();
        TopicMapper topicMapper = session.getMapper(TopicMapper.class);

        Topic topic = topicMapper.findByIdNew(1);
        Topic topic2 = topicMapper.findByIdNew(1);
        System.out.println(topic2.getTitle());
        System.out.println(topic2.getUser().getUsername());
        System.out.println(topic2.getNode().getNodename());

        session.commit();
        session.close();
    }

}