package com.kaishengit.service;

import com.kaishengit.dao.impl.NodeDao;
import com.kaishengit.pojo.Node;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class NodeService {

    @Inject
    private NodeDao nodeDao;

    @Transactional(readOnly = true,isolation = Isolation.REPEATABLE_READ)
    public Node fingById(Integer id){
        return nodeDao.findById(id);
    }

    public void save(){
        Node node = new Node();
        node.setNodename("N1");
        nodeDao.save(node);

        Node node2 = new Node();
        node2.setNodename("N2");
        nodeDao.save(node2);
    }
}
