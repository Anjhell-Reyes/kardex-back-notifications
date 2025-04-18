package com.kardex.infrastructure.out.adapter;

import com.kardex.domain.exception.TypeNotFoundException;
import com.kardex.domain.model.Type;
import com.kardex.domain.spi.ITypePersistencePort;
import com.kardex.infrastructure.out.mapper.TypeEntityMapper;
import com.kardex.infrastructure.out.repository.ITypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TypeJpaAdapter implements ITypePersistencePort {

    private final ITypeRepository typeRepository;
    private final TypeEntityMapper typeEntityMapper;

    @Override
    public Type getTypeById(Long typeId) {
        return typeEntityMapper.toType(typeRepository.findById(typeId).orElseThrow(TypeNotFoundException::new));
    }
}
