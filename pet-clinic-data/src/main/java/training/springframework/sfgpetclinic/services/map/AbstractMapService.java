package training.springframework.sfgpetclinic.services.map;

import training.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>{

    private Map<Long, T> map = new HashMap<>();

    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    public T findById(ID id){
        return map.get(id);
    }

    public T save(T entity) {
        if (entity !=null){
            if (Objects.isNull(entity.getId())){
                entity.setId(getNextId());
            }
            map.put(entity.getId(), entity);
        }else {
            throw new NullPointerException("Entity of class: "+entity.getClass().getName()+" cannot be null.");
        }
        return entity;
    }

    public void delete(T entity) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(entity));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

    public Long getNextId(){
        try{
           return Collections.max(map.keySet()) + 1;
        }catch (Exception e){
            return 1L;
        }
        //return map.isEmpty()?1L:Collections.max(map.keySet()) + 1;
    }
}
