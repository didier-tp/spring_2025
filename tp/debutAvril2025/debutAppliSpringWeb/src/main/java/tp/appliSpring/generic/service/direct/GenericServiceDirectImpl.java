package tp.appliSpring.generic.service.direct;

import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithId;
import tp.appliSpring.generic.service.GenericService;
import org.springframework.data.repository.CrudRepository;
import tp.appliSpring.generic.converter.GenericMapper;

import java.util.List;

/**
 * Implementation of GenericService that directly uses the CrudRepository for data access.
 * This implementation does not use SPI Loader/Saver abstraction or persistence adapter.
 *
 * @param <T>  the type of the model class
 * @param <E>  the type of the entity class
 * @param <ID> the type of the identifier
 */
public class GenericServiceDirectImpl<T extends WithId, E, ID> implements GenericService<T, ID> {

    private Class<T> modelClass;
    private Class<E> entityClass;
    private CrudRepository<E, ID> crudRepository;

    /**
     * Constructor for GenericServiceDirectImpl.
     *
     * @param modelClass    the class of the model
     * @param entityClass   the class of the entity
     * @param crudRepository the CrudRepository for data access
     */
    public GenericServiceDirectImpl(Class<T> modelClass,
                                    Class<E> entityClass,
                                    CrudRepository<E, ID> crudRepository) {
        this.modelClass = modelClass;
        this.entityClass = entityClass;
        this.crudRepository = crudRepository;
    }

    /**
     * Searches for an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the model object
     * @throws EntityNotFoundException if the entity is not found
     */
    @Override
    public T searchById(ID id) throws EntityNotFoundException {
        if (!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id=" + id);
        E e = crudRepository.findById(id).get();
        return GenericMapper.MAPPER.map(e, modelClass);
    }

    /**
     * Searches for all entities.
     *
     * @return a list of model objects
     */
    @Override
    public List<T> searchAll() {
        List<E> entityList = (List<E>) crudRepository.findAll();
        return GenericMapper.MAPPER.map(entityList, modelClass);
    }

    /**
     * Creates a new entity.
     *
     * @param obj the model object to create
     * @return the created model object
     */
    @Override
    public T create(T obj) {
        E entity = GenericMapper.MAPPER.map(obj, entityClass);
        E savedEntity = crudRepository.save(entity);
        return GenericMapper.MAPPER.map(savedEntity, modelClass);
    }

    /**
     * Updates an existing entity.
     *
     * @param obj the model object to update
     * @return the updated model object
     * @throws EntityNotFoundException if the entity is not found
     */
    @Override
    public T update(T obj) throws EntityNotFoundException {
        ID id = (ID) obj.extractId();
        if (!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id=" + id);
        E entity = GenericMapper.MAPPER.map(obj, entityClass);
        E updatedEntity = crudRepository.save(entity);
        return GenericMapper.MAPPER.map(updatedEntity, modelClass);
    }

    /**
     * Removes an entity by its ID.
     *
     * @param id the ID of the entity to remove
     * @throws EntityNotFoundException if the entity is not found
     */
    @Override
    public void removeById(ID id) throws EntityNotFoundException {
        if (!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id=" + id);
        crudRepository.deleteById(id);
    }
}
