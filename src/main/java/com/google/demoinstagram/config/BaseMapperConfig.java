package com.google.demoinstagram.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface BaseMapperConfig<E, M> extends Serializable {

    M convertToModel(E entity);

    E convertToEntity(M model);

    default List<M> convertToModels(List<E> entities) {
        if (entities == null) {
            return null;
        } else {
            ArrayList<M> list = new ArrayList<>();

            for (E ee : entities) {
                list.add(this.convertToModel(ee));
            }
            return list;
        }
    }

    default List<E> convertToEntities(List<M> models) {
        if (models == null) {
            return null;
        } else {
            ArrayList<E> list = new ArrayList<>();

            for (M mm : models) {
                list.add(this.convertToEntity(mm));
            }
            return list;
        }
    }
}
