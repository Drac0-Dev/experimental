package com.dracoapps.experimental.ui.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2  : Migration(1,2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE users")
        db.execSQL("CREATE TABLE users(" +
                "password TEXT," +
                "isAuth INTEGER," +
                "urlPic TEXT," +
                "description TEXT," +
                 "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "userId TEXT," +
                "email TEXT," +
                "username TEXT)"
            )
    }
}

/*
FATAL EXCEPTION: main
Process: com.myapp.experimental, 
java.lang.IllegalStateException: Migration didn't properly handle: users(com.dracoapps.experimental.ui.data.local.User).
Expected:
TableInfo
{name='users',
columns={
password=Column{name='password', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
isAuth=Column{name='isAuth', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
urlPic=Column{name='urlPic', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
description=Column{name='description', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},

id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='undefined'},
userId=Column{name='userId', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
email=Column{name='email', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
username=Column{name='username', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'}}, foreignKeys=[], indices=[]}
Found:
TableInfo
{name='users',
 columns={
 id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='undefined'},
 email=Column{name='email', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='undefined'},
 username=Column{name='username', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='undefined'},
 password=Column{name='password', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='undefined'},
 userId=Column{name='userId', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
 isAuth=Column{name='isAuth', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
 urlPic=Column{name='urlPic', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
 description=Column{name='description', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'}}, foreignKeys=[], indices=[]}
 */


/*
FATAL EXCEPTION: main
 Expected:
 TableInfo{
name='users',
columns={
password=Column{name='password', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
 isAuth=Column{name='isAuth', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
urlPic=Column{name='urlPic', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
description=Column{name='description', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='undefined'},
 userId=Column{name='userId', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
 email=Column{name='email', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
username=Column{name='username', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'}}, foreignKeys=[], indices=[]}
Found:
TableInfo{
name='users',
columns={
password=Column{name='password', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
isAuth=Column{name='isAuth', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
urlPic=Column{name='urlPic', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
description=Column{name='description', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
id=Column{name='id', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=1, defaultValue='undefined'},
userId=Column{name='userId', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
email=Column{name='email', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'},
username=Column{name='username', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0, defaultValue='undefined'}}, foreignKeys=[], indices=[]}

 */