package com.ganesh.splitwise_application.converters;

import com.ganesh.splitwise_application.DTO.*;
import com.ganesh.splitwise_application.models.Expense;
import com.ganesh.splitwise_application.models.Group;
import com.ganesh.splitwise_application.models.Transaction;
import com.ganesh.splitwise_application.models.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConverters implements Converters{
    ModelMapper mapper;
    ModelMapperConverters(ModelMapper mapper){
        this.mapper=mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<CreateUserDTO,User> createUserDTOUserTypeMap=mapper.createTypeMap(CreateUserDTO.class,User.class);
        TypeMap<User, GetUserDTO> userGetUserDTOTypeMap=mapper.createTypeMap(User.class,GetUserDTO.class);
        TypeMap<Group, GetGroupDto> groupGetGroupDtoTypeMap=mapper.createTypeMap(Group.class,GetGroupDto.class);
        groupGetGroupDtoTypeMap.addMappings(mapping->{
            mapping.map(Group::getUsers,GetGroupDto::setUsers);
        });
        TypeMap<CreateGroupDto,Group> createGroupDtoGroupTypeMap=mapper.createTypeMap(CreateGroupDto.class,Group.class);
        createGroupDtoGroupTypeMap.addMappings(mapping -> {
            mapping.skip(Group::setUsers);
            mapping.skip(Group::setCreatedBy);
            mapping.skip(Group::setExpenses);
        });
        TypeMap<CreateExpenseDto, Expense> createExpenseDtoExpenseTypeMap=mapper.createTypeMap(CreateExpenseDto.class,Expense.class);
        createExpenseDtoExpenseTypeMap.addMappings(mapping -> {
            mapping.skip(Expense::setCreatedBy);
            mapping.skip(Expense::setGroup);
            mapping.skip(Expense::setTransactions);
        });
        TypeMap<Expense, GetExpenseDto> expenseGetExpenseDtoTypeMap=mapper.createTypeMap(Expense.class,GetExpenseDto.class);
        expenseGetExpenseDtoTypeMap.addMappings(mapping -> {
            mapping.map(src->src.getCreatedBy().getName(),GetExpenseDto::setCreatedBy);
            mapping.map(src->src.getGroup().getName(),GetExpenseDto::setGroup);
        });
        TypeMap<Transaction, GetTransactionDto> transactionGetTransactionDtoTypeMap=mapper.createTypeMap(Transaction.class,GetTransactionDto.class);
        transactionGetTransactionDtoTypeMap.addMappings(mapping -> {
            mapping.map(src->src.getCreatedBy().getName(),GetTransactionDto::setCreatedBy);
            mapping.map(src->src.getExpense().getName(),GetTransactionDto::setExpense);
            mapping.map(src->src.getUser().getName(),GetTransactionDto::setUser);
        });
    }
    public User createUserDtoToUser(CreateUserDTO dto){
        return mapper.map(dto,User.class);
    }
    public GetUserDTO userToGetUserDTO(User user){
        if(user==null)
            return null;
        return mapper.map(user,GetUserDTO.class);
    }

    @Override
    public GetGroupDto groupToGetGroupDTO(Group g) {
        if(g==null)
            return null;
        return mapper.map(g,GetGroupDto.class);
    }

    @Override
    public Group createGroupDtoToGroup(CreateGroupDto dto) {
        return mapper.map(dto,Group.class);
    }

    public Expense createExpenseDtoToExpense(CreateExpenseDto dto){
        return mapper.map(dto,Expense.class);
    }
    public GetExpenseDto expenseToGetExpenseDto(Expense expense){
        if(expense==null)
            return null;
        return mapper.map(expense,GetExpenseDto.class);
    }
    public Transaction createTransactionDtoToTransaction(CreateTransactionDto dto){
        return mapper.map(dto,Transaction.class);
    }
    public GetTransactionDto transactionToGetTransactionDto(Transaction transaction){
        if(transaction==null)
            return null;
        return mapper.map(transaction,GetTransactionDto.class);
    }
}
