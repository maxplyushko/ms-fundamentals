package com.epam.songservice.service;

public interface CrudService<T, D> {

  T create(T resource);
  T findById(D id);
  void deleteById(D id);
}
