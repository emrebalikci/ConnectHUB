package com.emrebalikci.connecthub.entities.concretes;

import com.emrebalikci.connecthub.core.entities.User;
import com.emrebalikci.connecthub.core.utilities.Utils;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "system_employees")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class SystemEmployee extends User {

    @Column(name = "first_name", nullable = false, length = Utils.Const.MAX_FN)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = Utils.Const.MAX_LN)
    private String lastName;

}
