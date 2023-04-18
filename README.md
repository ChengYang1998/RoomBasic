# RoomBasic
A repository for the learning Room.

## Database类模板

```kotlin
@Database([Word::class], version = 3, exportSchema = false)     //修改Entity后必须更新数据库版本
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao: WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null
        fun getInstance(context: Context): WordDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordDatabase::class.java,
                        "word_database"
                    )
//                        .fallbackToDestructiveMigration()   //破坏性地重建
//                        .addMigrations(MIGRATION_2_3) //创建迁移策略
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private val MIGRATION_1_2: Migration = Migration(1, 2) {
            it.execSQL("ALTER TABLE word DROP COLUMN bar_data")
        }


        //数据库删除列 -> 新创建一个然后迁移数据
        private val MIGRATION_2_3: Migration = Migration(2, 3) {
            // 创建一个新的临时表
            it.execSQL(
                "CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, english_word TEXT NOT NULL, chinese_meaning TEXT NOT NULL)"
            )
            // 复制旧表中的数据到新表
            it.execSQL("INSERT INTO word_temp SELECT id, english_word, chinese_meaning FROM word")
            // 删除旧表
            it.execSQL("DROP TABLE word")
            // 将新表重命名为旧表的名称
            it.execSQL("ALTER TABLE word_temp RENAME TO word")

        }

    }
}
```

