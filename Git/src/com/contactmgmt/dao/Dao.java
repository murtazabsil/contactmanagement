package com.contactmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.contactmgmt.model.Contact;

public enum Dao {
  INSTANCE;

  public List<Contact> listContacts() {
    EntityManager em = EMFService.get().createEntityManager();
    // Read the existing entries
    Query q = em.createQuery("select c from Contact c");
    List<Contact> contacts = q.getResultList();
    return contacts;
  }

  public void add(String userId, String name, String contactinfo) {
    synchronized (this) {
      EntityManager em = EMFService.get().createEntityManager();
      Contact contact = new Contact(userId, name, contactinfo);
      em.persist(contact);
      em.close();
    }
  }

  public List<Contact> getContact(String userId) {
    EntityManager em = EMFService.get().createEntityManager();
    Query q = em
        .createQuery("select c from Contact c where c.author = :userId");
    q.setParameter("userId", userId);
    List<Contact> contacts = q.getResultList();
    return contacts;
  }

  public void remove(long id) {
    EntityManager em = EMFService.get().createEntityManager();
    try {
      Contact contact = em.find(Contact.class, id);
      em.remove(contact);
    } finally {
      em.close();
    }
  }
} 
