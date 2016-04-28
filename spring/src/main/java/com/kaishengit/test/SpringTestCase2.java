package com.kaishengit.test;

import com.kaishengit.dao.impl.NodeDao;
import com.kaishengit.pojo.Node;
import com.kaishengit.service.NodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTestCase2 {

    @Inject
    private NodeDao nodeDao;

    @Inject
    private NodeService nodeService;


    @Test
    public void testNode(){
        Node node = new Node();
        node.setNodename("spring2");

        nodeDao.save(node);
    }

    @Test
    public void testNodeUpdate(){
        Node node = nodeDao.findById(6);
        node.setNodename("SpringMVC");

        nodeDao.update(node);
    }


    @Test
    public void testFindAll(){
        List<Node> nodes = nodeDao.fingAll();
        Assert.assertEquals(nodes.size(),8);
    }

    @Test
    public void testNodeServicesSave(){
        nodeService.save();
    }
}
