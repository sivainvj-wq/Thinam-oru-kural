package com.thirukkural.app.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H\'J\u0011\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00032\u0006\u0010\f\u001a\u00020\tH\'J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010\u0010\u001a\u00020\tH\'J\u001f\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/thirukkural/app/data/db/KuralDao;", "", "getAllChapters", "Landroidx/lifecycle/LiveData;", "", "Lcom/thirukkural/app/data/db/ChapterInfo;", "getAllKurals", "Lcom/thirukkural/app/model/Kural;", "getCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKuralByNumber", "number", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKuralByNumberLive", "getKuralsByChapter", "chapter", "insertAll", "", "kurals", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchKurals", "query", "", "app_debug"})
@androidx.room.Dao
public abstract interface KuralDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<com.thirukkural.app.model.Kural> kurals, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM kurals WHERE number = :number")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getKuralByNumber(int number, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.thirukkural.app.model.Kural> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM kurals WHERE number = :number")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<com.thirukkural.app.model.Kural> getKuralByNumberLive(int number);
    
    @androidx.room.Query(value = "SELECT * FROM kurals ORDER BY number ASC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.thirukkural.app.model.Kural>> getAllKurals();
    
    @androidx.room.Query(value = "SELECT * FROM kurals WHERE chapter = :chapter ORDER BY number ASC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.thirukkural.app.model.Kural>> getKuralsByChapter(int chapter);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM kurals")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM kurals WHERE CAST(number AS TEXT) LIKE :query || \'%\' OR chapterNameEn LIKE \'%\' || :query || \'%\' OR chapterName LIKE \'%\' || :query || \'%\' OR line1 LIKE \'%\' || :query || \'%\' OR line2 LIKE \'%\' || :query || \'%\' OR translationEn LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.thirukkural.app.model.Kural>> searchKurals(@org.jetbrains.annotations.NotNull
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT DISTINCT chapter, chapterName, chapterNameEn, book, bookName, bookNameEn FROM kurals ORDER BY chapter ASC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.thirukkural.app.data.db.ChapterInfo>> getAllChapters();
}