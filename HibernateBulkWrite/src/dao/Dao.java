package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dto.Daughter;
import dto.GrandDaughter;
import dto.GrandMother;
import dto.Mother;


//total Milliseconds for 10: 2327 with sys.out
//total Milliseconds for 10: 2059 without sys.out
//total Milliseconds for 100: 112654 with sys.out
//total Milliseconds for 100: 93645 without sys.out
public class Dao {
	
	/*
	 * Jeder Datensatz wird in einem einzelnen INSERT geschrieben; langsam
	 */
	private static void noBatch(Configuration conf) {
		SessionFactory sessionFactory = conf.buildSessionFactory();
		long start = System.currentTimeMillis();
		int children = 2;
		Session session = null;
		session = sessionFactory.openSession();

		writeGrandmother(session);
		Transaction tx = null;
		session = sessionFactory.openSession();
		for(int i = 0; i < children; i++) {
			try {
				tx = session.beginTransaction();
				System.out.println(tx.isActive());
				Mother m = new Mother("mother" + i);
				m.setAge(60);
				for(int j = 0; j < children; j++) {
					Daughter d = new Daughter("d" + i + "_" + j);
					d.setAge(40);
					m.addDaughter(d);
					for(int k = 0; k < children; k++) {
						GrandDaughter gd = new GrandDaughter("gd" + i + "_" + j + "_" + k);
						gd.setAge(20);
						d.addDaughter(gd);
					}
				}
				session.save(m);
				session.flush();
				tx.commit();
			} catch(RuntimeException e) {
				if(tx != null) {
					tx.rollback();
					throw e;
				}
			} finally {
				session.close();
			}
		} 
		long stop = System.currentTimeMillis();
		long res = stop - start;
		System.out.println("total Milliseconds for " + children + ": " + res );
	}
	
	private static void writeGrandmother(Session session) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GrandMother root = new GrandMother("gm");
			root.setAge(99);
			session.save(root);
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null) {
				tx.rollback();
				throw e;
			}
		} /*finally {
			session.close();
		}*/
	}
	
	private static void batch(Configuration conf) {
		SessionFactory sessionFactory = conf.buildSessionFactory();
		long start = System.currentTimeMillis();
		int children = 10;
		int batchsize = 2;
		Session session = null;
		session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			GrandMother root = new GrandMother("gm");
			root.setAge(99);
			session.save(root);
			session.flush();
			tx.commit();
			for(int i = 0; i < children; i++) {
				Transaction mtx = session.beginTransaction();
				Mother m = new Mother("mother" + i);
				m.setAge(60);
				root.addDaughter(m);
				for(int j = 0; j < children; j++) {
					Daughter d = new Daughter("d" + i + "_" + j);
					d.setAge(40);
					m.addDaughter(d);
					for(int k = 0; k < children; k++) {
						GrandDaughter gd = new GrandDaughter("gd" + i + "_" + j + "_" + k);
						gd.setAge(20);
						d.addDaughter(gd);
					}
				}
				session.save(m);
				if(i % batchsize == 0) {
					session.flush();
					session.clear();
				}
			}
		} catch(RuntimeException e) {
			if(tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			session.close();
		}
		long stop = System.currentTimeMillis();
		long res = stop - start;
		System.out.println("total Milliseconds for " + children + ": " + res );
	}
	
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.configure();
		noBatch(conf);
//		batch(conf);
	}

}
