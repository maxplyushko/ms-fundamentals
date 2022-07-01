package com.epam.songservice.service;

import java.util.List;

public interface CrudService<T, D> {

  T create(T resource);
  List<T> findAll();
  T findById(D id);
  void deleteById(D id);
}
