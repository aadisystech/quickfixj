create user 'fiximulator'@'localhost' identified by 'fiximulator';

grant all privileges on quickfix.* 
to 'fiximulator'@'localhost' WITH grant option;