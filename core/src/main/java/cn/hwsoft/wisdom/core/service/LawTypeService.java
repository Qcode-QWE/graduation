package cn.hwsoft.wisdom.core.service;

import cn.hwsoft.wisdom.core.domain.Law_type;

import java.util.List;

/**
 * Created by Lenovo on 2019/7/28.
 */
public interface LawTypeService {
    Law_type  searchById(Integer law_type_id);

    List<Law_type> searchAll();

    boolean delete(Integer id);

    boolean add(Law_type type);

    boolean chageStatus(Integer id);

    List<Law_type> search(Byte status);

    boolean update(Integer id, String title);
}
