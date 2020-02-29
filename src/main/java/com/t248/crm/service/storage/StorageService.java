package com.t248.crm.service.storage;

import com.t248.crm.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StorageService {
    public Page<Storage> finds(String prodName, String stkWarehouse, Pageable pageable);
}
