package me.dusanov.unida.jwt;

import java.util.ArrayList;
import java.util.List;

public class CREATE_DB_SQL {

    public static final List<String> SQL = new ArrayList<>();

    static {
    SQL.add("drop table if exists user;");
    SQL.add("drop table if exists user_roles;");
    SQL.add("drop table if exists roles_perms;");
    SQL.add("create table user (username varchar(255), password varchar(255), password_salt varchar(255) );");
    SQL.add("create table user_roles (username varchar(255), role varchar(255));");
    SQL.add("create table roles_perms (role varchar(255), perm varchar(255));");

    SQL.add("ALTER TABLE user_roles ADD CONSTRAINT pk_user_roles PRIMARY KEY (username, role);");
    SQL.add("ALTER TABLE roles_perms ADD CONSTRAINT pk_roles_perms PRIMARY KEY (role, perm);");

    SQL.add("insert into user values ('tim', 'EC0D6302E35B7E792DF9DA4A5FE0DB3B90FCAB65A6215215771BF96D498A01DA8234769E1CE8269A105E9112F374FDAB2158E7DA58CDC1348A732351C38E12A0', 'C59EB438D1E24CACA2B1A48BC129348589D49303858E493FBE906A9158B7D5DC');");
    SQL.add("insert into user_roles values ('tim', 'dev');");
    SQL.add("insert into user_roles values ('tim', 'admin');");
    SQL.add("insert into roles_perms values ('dev', 'commit_code');");
    SQL.add("insert into roles_perms values ('dev', 'eat_pizza');");
    SQL.add("insert into roles_perms values ('admin', 'merge_pr');");

    // add another user using nonces
    SQL.add("insert into user values ('paulo', '4EFC18C18180F20905B79EA06D24F866382E9888957195E3C36EFA603C5194AD4E56685579FC4A9C5144EE093B00E1E208C344E80703DEEE28D4FCF3C7778F24$0', 'E1BDFAF66074169738F593626ABDE48E013CA17D87CDFF07F18FC5D7FBBFA427');");

    // and a second set of tables with slight differences

    SQL.add("drop table if exists user2;");
    SQL.add("drop table if exists user_roles2;");
    SQL.add("drop table if exists roles_perms2;");
    SQL.add("create table user2 (user_name varchar(255), pwd varchar(255), pwd_salt varchar(255) );");
    SQL.add("create table user_roles2 (user_name varchar(255), role varchar(255));");
    SQL.add("create table roles_perms2 (role varchar(255), perm varchar(255));");

    SQL.add("insert into user2 values ('tim', 'EC0D6302E35B7E792DF9DA4A5FE0DB3B90FCAB65A6215215771BF96D498A01DA8234769E1CE8269A105E9112F374FDAB2158E7DA58CDC1348A732351C38E12A0', 'C59EB438D1E24CACA2B1A48BC129348589D49303858E493FBE906A9158B7D5DC');");
    SQL.add("insert into user_roles2 values ('tim', 'dev');");
    SQL.add("insert into user_roles2 values ('tim', 'admin');");
    SQL.add("insert into roles_perms2 values ('dev', 'commit_code');");
    SQL.add("insert into roles_perms2 values ('dev', 'eat_pizza');");
    SQL.add("insert into roles_perms2 values ('admin', 'merge_pr');");

    // add another user using nonces
    SQL.add("insert into user2 values ('paulo', '4EFC18C18180F20905B79EA06D24F866382E9888957195E3C36EFA603C5194AD4E56685579FC4A9C5144EE093B00E1E208C344E80703DEEE28D4FCF3C7778F24$0', 'E1BDFAF66074169738F593626ABDE48E013CA17D87CDFF07F18FC5D7FBBFA427');");
  }

}
