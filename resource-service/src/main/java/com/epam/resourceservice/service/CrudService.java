package com.epam.resourceservice.service;

public interface CrudService<T, D> {

  T create(T resource);
  T findById(D id);
  void delete(T resource);
}
