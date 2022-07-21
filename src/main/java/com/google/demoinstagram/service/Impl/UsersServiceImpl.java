package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.baseData.Constant;
import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.enums.DataStateEnum;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.excption.service.UsersValidator;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.mapper.impl.UsersMapperImpl;
import com.google.demoinstagram.model.UsersModel;
import com.google.demoinstagram.repository.UsersRepository;
import com.google.demoinstagram.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    public static UsersMapper usersMapper = new UsersMapperImpl();
    private final UsersRepository usersRepository;
    private final UsersValidator usersValidator;

    private EntityManager em;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public UsersModel saveUser(UsersModel usersModel) throws Exception {
        usersValidator.validate(usersModel);
        return usersMapper.convertToModel(usersRepository.save(usersMapper.convertToEntity(usersModel)));
    }

    @Override
    public Page<UsersModel> getAllUser(UsersModel usersModel, Pageable pageable) {
        int totalCount = count(usersModel);
        return new PageImpl<>(usersMapper.convertToModels(list(usersModel, pageable)), pageable, totalCount);
    }

    private int count(UsersModel usersModel) {
        StringBuilder strQuery = new StringBuilder("SELECT COUNT(*) FROM users WHERE (1=1) AND data_state = 0 ");
        addFilters(usersModel, strQuery);
        return Math.toIntExact(Long.parseLong(em.createNativeQuery(strQuery.toString()).getSingleResult().toString()));
    }

    private List<Users> list(UsersModel usersModel, Pageable pageable) {
        StringBuilder strQuery = new StringBuilder("SELECT * FROM users WHERE (1=1) AND data_state = 0 ");
        addFilters(usersModel, strQuery);
        strQuery.append(Constant.LIMIT).append(Constant.PREFIX).append(pageable.getPageSize()).append(Constant.PREFIX);
        List<Object[]> objectList = em.createNativeQuery(strQuery.toString()).getResultList();
        List<Users> users = new ArrayList<>();
        for (Object[] objects : objectList) {
            Users newUser = new Users();
            newUser.setId(Long.valueOf(String.valueOf(objects[0])));
            newUser.setCover((String) objects[1]);
            newUser.setCreateDate((Date) objects[2]);
            newUser.setDataState((Integer) objects[3]);
            newUser.setEmail((String) objects[4]);
            newUser.setNumber((String) objects[5]);
            newUser.setPassword((String) objects[6]);
            newUser.setUpdateDate((Date) objects[7]);
            newUser.setUsername((String) objects[8]);
            users.add(newUser);
        }
        return users;
    }

    private void addFilters(UsersModel usersModel, StringBuilder strQuery) {
        if (usersModel.getUsername() != null && usersModel.getUsername().length() > 0 && usersModel.getUsername().trim().length() > 0)
            strQuery.append(Constant.AND).append("username").append(Constant.LIKE)
                    .append(Constant.SINGLE_QUOTATION).append(usersModel.getUsername()).append(Constant.SINGLE_QUOTATION).append(Constant.PREFIX);

        if (usersModel.getEmail() != null && usersModel.getEmail().length() > 0 && usersModel.getEmail().trim().length() > 0)
            strQuery.append(Constant.AND).append("email").append(Constant.LIKE)
                    .append(Constant.SINGLE_QUOTATION).append(usersModel.getEmail()).append(Constant.SINGLE_QUOTATION).append(Constant.PREFIX);
    }

    @Override
    public UsersModel getUserById(Long id) {
        return usersMapper.convertToModel(usersRepository.find(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id)));
    }

    @Transactional
    @Override
    public UsersModel updateUser(UsersModel updateModel, Long id) throws Exception {
        usersValidator.validate(updateModel);
        Users existingUser = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Users", "id", id));
        existingUser.setUsername(updateModel.getUsername());
        existingUser.setPassword(updateModel.getPassword());
        existingUser.setEmail(updateModel.getEmail());
        existingUser.setNumber(updateModel.getNumber());
        existingUser.setCover(updateModel.getCover());
        Users updatedModel = usersRepository.save(existingUser);
        return usersMapper.convertToModel(updatedModel);
    }

    @Override
    public void deleteUser(Long id) {
        // check whether a User exist in a DB or not
        Users updateDataState = usersRepository.find(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id));
        updateDataState.setDataState(DataStateEnum.HUNDRED.getValue());
        usersRepository.save(updateDataState);
    }
}
