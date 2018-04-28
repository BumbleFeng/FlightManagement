package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.BaggageCarousel;
import edu.neu.csye6220.pojo.Terminal;

public class BaggageCarouselDAO extends DAO{
	
	public void add(BaggageCarousel baggageCarousel) {
		try {
			begin();
			getSession().save(baggageCarousel);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public BaggageCarousel undefined(Terminal terminal) {
		BaggageCarousel baggageCarousel = null;
		try {
			begin();
			Query q = getSession().createQuery("from BaggageCarousel where terminal= :terminal and baggageCarouselCode= :baggageCarouselCode");
			q.setParameter("terminal", terminal);
			q.setParameter("baggageCarouselCode", "undefined");
			baggageCarousel = (BaggageCarousel) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return baggageCarousel;
	}
}
