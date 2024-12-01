package com.example.mobile_banking.api.user;



import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.api.user.request.UserRequest;
import org.mapstruct.Mapper;


// this interface used for mapped between entity and dto
@Mapper(componentModel = "spring")
public interface UserMapper {

    // now we បំលែង  user tov jea userDto, so  we need to use User as a parameter
    //So we consider User as a source and UserDto as a target
    //converts a  single user  entity to a userDto
    UserDto userToUserDto(User user);

    //this method is intended to convert a  collection of User objects into a collection of UserDto object
    Iterable<UserDto> usersToUserDto(Iterable<User> users);

    //convert UserRequest to User entity  when creating new user
    User createUserDtoToUser(UserRequest userRequest);
}
