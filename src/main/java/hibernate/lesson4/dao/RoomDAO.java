package hibernate.lesson4.dao;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        super(Room.class);
    }

    public Room save(Room room) throws Exception {

        return saveEntity(room);
    }

    public Room update(Room room) throws Exception {

        return updateEntity(room);
    }

    public Room findById(long id) throws Exception {

        return findEntityById(id);
    }

    public void delete(long id) throws Exception {

        deleteEntity(id);
    }

    public Room getRoomByFilter() {

        Session session = getSessionFactory().openSession();

        Query<Room> query = session.createQuery("from Room where (true or id>3) ", Room.class);

        //query.setParameter("w","Palace");
        //query.setParameter(2,"qq");


        List<Room> rooms = query.list();
/*
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> query = builder.createQuery(Room.class);
        Root<Room> root = query.from(Room.class);
        Join<Room, Hotel> join = root.join("hotel");

        Float f = 700f;

        Predicate predicate = builder.lessThan(root.get("price"), 700);
        Predicate predicate1 = builder.conjunction();

        query.select(root).where(predicate1);

        EntityGraph<Room> fetchGraph = session.createEntityGraph(Room.class);
        fetchGraph.addSubgraph("hotel");
        TypedQuery<Room> q = session.createQuery(query).setHint("javax.persistence.loadgraph", fetchGraph);
        List<Room> allitems = q.getResultList();
*/


        return null;
    }
}
