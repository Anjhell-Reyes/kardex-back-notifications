package com.kardex.domain.spi;

import com.kardex.domain.model.Type;

public interface ITypePersistencePort {

    Type getTypeById(Long typeId);
}
