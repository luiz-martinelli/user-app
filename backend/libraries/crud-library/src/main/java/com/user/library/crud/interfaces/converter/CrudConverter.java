package com.user.library.crud.interfaces.converter;


public abstract class CrudConverter<Entity, Dto> {

    public abstract Dto createDto(Entity entity);

    public abstract Entity createEntity(Dto dto);
}
