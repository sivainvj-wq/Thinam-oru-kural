package com.thirukkural.app.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.thirukkural.app.model.Kural;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class KuralDao_Impl implements KuralDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Kural> __insertionAdapterOfKural;

  public KuralDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfKural = new EntityInsertionAdapter<Kural>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `kurals` (`number`,`chapter`,`chapterName`,`chapterNameEn`,`book`,`bookName`,`bookNameEn`,`line1`,`line2`,`translationEn`,`explanationTa`,`explanationEn`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Kural entity) {
        statement.bindLong(1, entity.getNumber());
        statement.bindLong(2, entity.getChapter());
        if (entity.getChapterName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getChapterName());
        }
        if (entity.getChapterNameEn() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getChapterNameEn());
        }
        statement.bindLong(5, entity.getBook());
        if (entity.getBookName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBookName());
        }
        if (entity.getBookNameEn() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBookNameEn());
        }
        if (entity.getLine1() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLine1());
        }
        if (entity.getLine2() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getLine2());
        }
        if (entity.getTranslationEn() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getTranslationEn());
        }
        if (entity.getExplanationTa() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getExplanationTa());
        }
        if (entity.getExplanationEn() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getExplanationEn());
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<Kural> kurals, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfKural.insert(kurals);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getKuralByNumber(final int number, final Continuation<? super Kural> $completion) {
    final String _sql = "SELECT * FROM kurals WHERE number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, number);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Kural>() {
      @Override
      @Nullable
      public Kural call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfChapterName = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterName");
          final int _cursorIndexOfChapterNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameEn");
          final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
          final int _cursorIndexOfBookName = CursorUtil.getColumnIndexOrThrow(_cursor, "bookName");
          final int _cursorIndexOfBookNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameEn");
          final int _cursorIndexOfLine1 = CursorUtil.getColumnIndexOrThrow(_cursor, "line1");
          final int _cursorIndexOfLine2 = CursorUtil.getColumnIndexOrThrow(_cursor, "line2");
          final int _cursorIndexOfTranslationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "translationEn");
          final int _cursorIndexOfExplanationTa = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationTa");
          final int _cursorIndexOfExplanationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationEn");
          final Kural _result;
          if (_cursor.moveToFirst()) {
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final String _tmpChapterName;
            if (_cursor.isNull(_cursorIndexOfChapterName)) {
              _tmpChapterName = null;
            } else {
              _tmpChapterName = _cursor.getString(_cursorIndexOfChapterName);
            }
            final String _tmpChapterNameEn;
            if (_cursor.isNull(_cursorIndexOfChapterNameEn)) {
              _tmpChapterNameEn = null;
            } else {
              _tmpChapterNameEn = _cursor.getString(_cursorIndexOfChapterNameEn);
            }
            final int _tmpBook;
            _tmpBook = _cursor.getInt(_cursorIndexOfBook);
            final String _tmpBookName;
            if (_cursor.isNull(_cursorIndexOfBookName)) {
              _tmpBookName = null;
            } else {
              _tmpBookName = _cursor.getString(_cursorIndexOfBookName);
            }
            final String _tmpBookNameEn;
            if (_cursor.isNull(_cursorIndexOfBookNameEn)) {
              _tmpBookNameEn = null;
            } else {
              _tmpBookNameEn = _cursor.getString(_cursorIndexOfBookNameEn);
            }
            final String _tmpLine1;
            if (_cursor.isNull(_cursorIndexOfLine1)) {
              _tmpLine1 = null;
            } else {
              _tmpLine1 = _cursor.getString(_cursorIndexOfLine1);
            }
            final String _tmpLine2;
            if (_cursor.isNull(_cursorIndexOfLine2)) {
              _tmpLine2 = null;
            } else {
              _tmpLine2 = _cursor.getString(_cursorIndexOfLine2);
            }
            final String _tmpTranslationEn;
            if (_cursor.isNull(_cursorIndexOfTranslationEn)) {
              _tmpTranslationEn = null;
            } else {
              _tmpTranslationEn = _cursor.getString(_cursorIndexOfTranslationEn);
            }
            final String _tmpExplanationTa;
            if (_cursor.isNull(_cursorIndexOfExplanationTa)) {
              _tmpExplanationTa = null;
            } else {
              _tmpExplanationTa = _cursor.getString(_cursorIndexOfExplanationTa);
            }
            final String _tmpExplanationEn;
            if (_cursor.isNull(_cursorIndexOfExplanationEn)) {
              _tmpExplanationEn = null;
            } else {
              _tmpExplanationEn = _cursor.getString(_cursorIndexOfExplanationEn);
            }
            _result = new Kural(_tmpNumber,_tmpChapter,_tmpChapterName,_tmpChapterNameEn,_tmpBook,_tmpBookName,_tmpBookNameEn,_tmpLine1,_tmpLine2,_tmpTranslationEn,_tmpExplanationTa,_tmpExplanationEn);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<Kural> getKuralByNumberLive(final int number) {
    final String _sql = "SELECT * FROM kurals WHERE number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, number);
    return __db.getInvalidationTracker().createLiveData(new String[] {"kurals"}, false, new Callable<Kural>() {
      @Override
      @Nullable
      public Kural call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfChapterName = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterName");
          final int _cursorIndexOfChapterNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameEn");
          final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
          final int _cursorIndexOfBookName = CursorUtil.getColumnIndexOrThrow(_cursor, "bookName");
          final int _cursorIndexOfBookNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameEn");
          final int _cursorIndexOfLine1 = CursorUtil.getColumnIndexOrThrow(_cursor, "line1");
          final int _cursorIndexOfLine2 = CursorUtil.getColumnIndexOrThrow(_cursor, "line2");
          final int _cursorIndexOfTranslationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "translationEn");
          final int _cursorIndexOfExplanationTa = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationTa");
          final int _cursorIndexOfExplanationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationEn");
          final Kural _result;
          if (_cursor.moveToFirst()) {
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final String _tmpChapterName;
            if (_cursor.isNull(_cursorIndexOfChapterName)) {
              _tmpChapterName = null;
            } else {
              _tmpChapterName = _cursor.getString(_cursorIndexOfChapterName);
            }
            final String _tmpChapterNameEn;
            if (_cursor.isNull(_cursorIndexOfChapterNameEn)) {
              _tmpChapterNameEn = null;
            } else {
              _tmpChapterNameEn = _cursor.getString(_cursorIndexOfChapterNameEn);
            }
            final int _tmpBook;
            _tmpBook = _cursor.getInt(_cursorIndexOfBook);
            final String _tmpBookName;
            if (_cursor.isNull(_cursorIndexOfBookName)) {
              _tmpBookName = null;
            } else {
              _tmpBookName = _cursor.getString(_cursorIndexOfBookName);
            }
            final String _tmpBookNameEn;
            if (_cursor.isNull(_cursorIndexOfBookNameEn)) {
              _tmpBookNameEn = null;
            } else {
              _tmpBookNameEn = _cursor.getString(_cursorIndexOfBookNameEn);
            }
            final String _tmpLine1;
            if (_cursor.isNull(_cursorIndexOfLine1)) {
              _tmpLine1 = null;
            } else {
              _tmpLine1 = _cursor.getString(_cursorIndexOfLine1);
            }
            final String _tmpLine2;
            if (_cursor.isNull(_cursorIndexOfLine2)) {
              _tmpLine2 = null;
            } else {
              _tmpLine2 = _cursor.getString(_cursorIndexOfLine2);
            }
            final String _tmpTranslationEn;
            if (_cursor.isNull(_cursorIndexOfTranslationEn)) {
              _tmpTranslationEn = null;
            } else {
              _tmpTranslationEn = _cursor.getString(_cursorIndexOfTranslationEn);
            }
            final String _tmpExplanationTa;
            if (_cursor.isNull(_cursorIndexOfExplanationTa)) {
              _tmpExplanationTa = null;
            } else {
              _tmpExplanationTa = _cursor.getString(_cursorIndexOfExplanationTa);
            }
            final String _tmpExplanationEn;
            if (_cursor.isNull(_cursorIndexOfExplanationEn)) {
              _tmpExplanationEn = null;
            } else {
              _tmpExplanationEn = _cursor.getString(_cursorIndexOfExplanationEn);
            }
            _result = new Kural(_tmpNumber,_tmpChapter,_tmpChapterName,_tmpChapterNameEn,_tmpBook,_tmpBookName,_tmpBookNameEn,_tmpLine1,_tmpLine2,_tmpTranslationEn,_tmpExplanationTa,_tmpExplanationEn);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Kural>> getAllKurals() {
    final String _sql = "SELECT * FROM kurals ORDER BY number ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"kurals"}, false, new Callable<List<Kural>>() {
      @Override
      @Nullable
      public List<Kural> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfChapterName = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterName");
          final int _cursorIndexOfChapterNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameEn");
          final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
          final int _cursorIndexOfBookName = CursorUtil.getColumnIndexOrThrow(_cursor, "bookName");
          final int _cursorIndexOfBookNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameEn");
          final int _cursorIndexOfLine1 = CursorUtil.getColumnIndexOrThrow(_cursor, "line1");
          final int _cursorIndexOfLine2 = CursorUtil.getColumnIndexOrThrow(_cursor, "line2");
          final int _cursorIndexOfTranslationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "translationEn");
          final int _cursorIndexOfExplanationTa = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationTa");
          final int _cursorIndexOfExplanationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationEn");
          final List<Kural> _result = new ArrayList<Kural>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Kural _item;
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final String _tmpChapterName;
            if (_cursor.isNull(_cursorIndexOfChapterName)) {
              _tmpChapterName = null;
            } else {
              _tmpChapterName = _cursor.getString(_cursorIndexOfChapterName);
            }
            final String _tmpChapterNameEn;
            if (_cursor.isNull(_cursorIndexOfChapterNameEn)) {
              _tmpChapterNameEn = null;
            } else {
              _tmpChapterNameEn = _cursor.getString(_cursorIndexOfChapterNameEn);
            }
            final int _tmpBook;
            _tmpBook = _cursor.getInt(_cursorIndexOfBook);
            final String _tmpBookName;
            if (_cursor.isNull(_cursorIndexOfBookName)) {
              _tmpBookName = null;
            } else {
              _tmpBookName = _cursor.getString(_cursorIndexOfBookName);
            }
            final String _tmpBookNameEn;
            if (_cursor.isNull(_cursorIndexOfBookNameEn)) {
              _tmpBookNameEn = null;
            } else {
              _tmpBookNameEn = _cursor.getString(_cursorIndexOfBookNameEn);
            }
            final String _tmpLine1;
            if (_cursor.isNull(_cursorIndexOfLine1)) {
              _tmpLine1 = null;
            } else {
              _tmpLine1 = _cursor.getString(_cursorIndexOfLine1);
            }
            final String _tmpLine2;
            if (_cursor.isNull(_cursorIndexOfLine2)) {
              _tmpLine2 = null;
            } else {
              _tmpLine2 = _cursor.getString(_cursorIndexOfLine2);
            }
            final String _tmpTranslationEn;
            if (_cursor.isNull(_cursorIndexOfTranslationEn)) {
              _tmpTranslationEn = null;
            } else {
              _tmpTranslationEn = _cursor.getString(_cursorIndexOfTranslationEn);
            }
            final String _tmpExplanationTa;
            if (_cursor.isNull(_cursorIndexOfExplanationTa)) {
              _tmpExplanationTa = null;
            } else {
              _tmpExplanationTa = _cursor.getString(_cursorIndexOfExplanationTa);
            }
            final String _tmpExplanationEn;
            if (_cursor.isNull(_cursorIndexOfExplanationEn)) {
              _tmpExplanationEn = null;
            } else {
              _tmpExplanationEn = _cursor.getString(_cursorIndexOfExplanationEn);
            }
            _item = new Kural(_tmpNumber,_tmpChapter,_tmpChapterName,_tmpChapterNameEn,_tmpBook,_tmpBookName,_tmpBookNameEn,_tmpLine1,_tmpLine2,_tmpTranslationEn,_tmpExplanationTa,_tmpExplanationEn);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Kural>> getKuralsByChapter(final int chapter) {
    final String _sql = "SELECT * FROM kurals WHERE chapter = ? ORDER BY number ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, chapter);
    return __db.getInvalidationTracker().createLiveData(new String[] {"kurals"}, false, new Callable<List<Kural>>() {
      @Override
      @Nullable
      public List<Kural> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfChapterName = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterName");
          final int _cursorIndexOfChapterNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameEn");
          final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
          final int _cursorIndexOfBookName = CursorUtil.getColumnIndexOrThrow(_cursor, "bookName");
          final int _cursorIndexOfBookNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameEn");
          final int _cursorIndexOfLine1 = CursorUtil.getColumnIndexOrThrow(_cursor, "line1");
          final int _cursorIndexOfLine2 = CursorUtil.getColumnIndexOrThrow(_cursor, "line2");
          final int _cursorIndexOfTranslationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "translationEn");
          final int _cursorIndexOfExplanationTa = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationTa");
          final int _cursorIndexOfExplanationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationEn");
          final List<Kural> _result = new ArrayList<Kural>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Kural _item;
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final String _tmpChapterName;
            if (_cursor.isNull(_cursorIndexOfChapterName)) {
              _tmpChapterName = null;
            } else {
              _tmpChapterName = _cursor.getString(_cursorIndexOfChapterName);
            }
            final String _tmpChapterNameEn;
            if (_cursor.isNull(_cursorIndexOfChapterNameEn)) {
              _tmpChapterNameEn = null;
            } else {
              _tmpChapterNameEn = _cursor.getString(_cursorIndexOfChapterNameEn);
            }
            final int _tmpBook;
            _tmpBook = _cursor.getInt(_cursorIndexOfBook);
            final String _tmpBookName;
            if (_cursor.isNull(_cursorIndexOfBookName)) {
              _tmpBookName = null;
            } else {
              _tmpBookName = _cursor.getString(_cursorIndexOfBookName);
            }
            final String _tmpBookNameEn;
            if (_cursor.isNull(_cursorIndexOfBookNameEn)) {
              _tmpBookNameEn = null;
            } else {
              _tmpBookNameEn = _cursor.getString(_cursorIndexOfBookNameEn);
            }
            final String _tmpLine1;
            if (_cursor.isNull(_cursorIndexOfLine1)) {
              _tmpLine1 = null;
            } else {
              _tmpLine1 = _cursor.getString(_cursorIndexOfLine1);
            }
            final String _tmpLine2;
            if (_cursor.isNull(_cursorIndexOfLine2)) {
              _tmpLine2 = null;
            } else {
              _tmpLine2 = _cursor.getString(_cursorIndexOfLine2);
            }
            final String _tmpTranslationEn;
            if (_cursor.isNull(_cursorIndexOfTranslationEn)) {
              _tmpTranslationEn = null;
            } else {
              _tmpTranslationEn = _cursor.getString(_cursorIndexOfTranslationEn);
            }
            final String _tmpExplanationTa;
            if (_cursor.isNull(_cursorIndexOfExplanationTa)) {
              _tmpExplanationTa = null;
            } else {
              _tmpExplanationTa = _cursor.getString(_cursorIndexOfExplanationTa);
            }
            final String _tmpExplanationEn;
            if (_cursor.isNull(_cursorIndexOfExplanationEn)) {
              _tmpExplanationEn = null;
            } else {
              _tmpExplanationEn = _cursor.getString(_cursorIndexOfExplanationEn);
            }
            _item = new Kural(_tmpNumber,_tmpChapter,_tmpChapterName,_tmpChapterNameEn,_tmpBook,_tmpBookName,_tmpBookNameEn,_tmpLine1,_tmpLine2,_tmpTranslationEn,_tmpExplanationTa,_tmpExplanationEn);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM kurals";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Kural>> searchKurals(final String query) {
    final String _sql = "SELECT * FROM kurals WHERE chapterNameEn LIKE '%' || ? || '%' OR chapterName LIKE '%' || ? || '%' OR line1 LIKE '%' || ? || '%' OR line2 LIKE '%' || ? || '%' OR translationEn LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 3;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 4;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 5;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"kurals"}, false, new Callable<List<Kural>>() {
      @Override
      @Nullable
      public List<Kural> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfChapter = CursorUtil.getColumnIndexOrThrow(_cursor, "chapter");
          final int _cursorIndexOfChapterName = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterName");
          final int _cursorIndexOfChapterNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterNameEn");
          final int _cursorIndexOfBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book");
          final int _cursorIndexOfBookName = CursorUtil.getColumnIndexOrThrow(_cursor, "bookName");
          final int _cursorIndexOfBookNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "bookNameEn");
          final int _cursorIndexOfLine1 = CursorUtil.getColumnIndexOrThrow(_cursor, "line1");
          final int _cursorIndexOfLine2 = CursorUtil.getColumnIndexOrThrow(_cursor, "line2");
          final int _cursorIndexOfTranslationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "translationEn");
          final int _cursorIndexOfExplanationTa = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationTa");
          final int _cursorIndexOfExplanationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "explanationEn");
          final List<Kural> _result = new ArrayList<Kural>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Kural _item;
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final String _tmpChapterName;
            if (_cursor.isNull(_cursorIndexOfChapterName)) {
              _tmpChapterName = null;
            } else {
              _tmpChapterName = _cursor.getString(_cursorIndexOfChapterName);
            }
            final String _tmpChapterNameEn;
            if (_cursor.isNull(_cursorIndexOfChapterNameEn)) {
              _tmpChapterNameEn = null;
            } else {
              _tmpChapterNameEn = _cursor.getString(_cursorIndexOfChapterNameEn);
            }
            final int _tmpBook;
            _tmpBook = _cursor.getInt(_cursorIndexOfBook);
            final String _tmpBookName;
            if (_cursor.isNull(_cursorIndexOfBookName)) {
              _tmpBookName = null;
            } else {
              _tmpBookName = _cursor.getString(_cursorIndexOfBookName);
            }
            final String _tmpBookNameEn;
            if (_cursor.isNull(_cursorIndexOfBookNameEn)) {
              _tmpBookNameEn = null;
            } else {
              _tmpBookNameEn = _cursor.getString(_cursorIndexOfBookNameEn);
            }
            final String _tmpLine1;
            if (_cursor.isNull(_cursorIndexOfLine1)) {
              _tmpLine1 = null;
            } else {
              _tmpLine1 = _cursor.getString(_cursorIndexOfLine1);
            }
            final String _tmpLine2;
            if (_cursor.isNull(_cursorIndexOfLine2)) {
              _tmpLine2 = null;
            } else {
              _tmpLine2 = _cursor.getString(_cursorIndexOfLine2);
            }
            final String _tmpTranslationEn;
            if (_cursor.isNull(_cursorIndexOfTranslationEn)) {
              _tmpTranslationEn = null;
            } else {
              _tmpTranslationEn = _cursor.getString(_cursorIndexOfTranslationEn);
            }
            final String _tmpExplanationTa;
            if (_cursor.isNull(_cursorIndexOfExplanationTa)) {
              _tmpExplanationTa = null;
            } else {
              _tmpExplanationTa = _cursor.getString(_cursorIndexOfExplanationTa);
            }
            final String _tmpExplanationEn;
            if (_cursor.isNull(_cursorIndexOfExplanationEn)) {
              _tmpExplanationEn = null;
            } else {
              _tmpExplanationEn = _cursor.getString(_cursorIndexOfExplanationEn);
            }
            _item = new Kural(_tmpNumber,_tmpChapter,_tmpChapterName,_tmpChapterNameEn,_tmpBook,_tmpBookName,_tmpBookNameEn,_tmpLine1,_tmpLine2,_tmpTranslationEn,_tmpExplanationTa,_tmpExplanationEn);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<ChapterInfo>> getAllChapters() {
    final String _sql = "SELECT DISTINCT chapter, chapterName, chapterNameEn, book, bookName, bookNameEn FROM kurals ORDER BY chapter ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"kurals"}, false, new Callable<List<ChapterInfo>>() {
      @Override
      @Nullable
      public List<ChapterInfo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfChapter = 0;
          final int _cursorIndexOfChapterName = 1;
          final int _cursorIndexOfChapterNameEn = 2;
          final int _cursorIndexOfBook = 3;
          final int _cursorIndexOfBookName = 4;
          final int _cursorIndexOfBookNameEn = 5;
          final List<ChapterInfo> _result = new ArrayList<ChapterInfo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChapterInfo _item;
            final int _tmpChapter;
            _tmpChapter = _cursor.getInt(_cursorIndexOfChapter);
            final String _tmpChapterName;
            if (_cursor.isNull(_cursorIndexOfChapterName)) {
              _tmpChapterName = null;
            } else {
              _tmpChapterName = _cursor.getString(_cursorIndexOfChapterName);
            }
            final String _tmpChapterNameEn;
            if (_cursor.isNull(_cursorIndexOfChapterNameEn)) {
              _tmpChapterNameEn = null;
            } else {
              _tmpChapterNameEn = _cursor.getString(_cursorIndexOfChapterNameEn);
            }
            final int _tmpBook;
            _tmpBook = _cursor.getInt(_cursorIndexOfBook);
            final String _tmpBookName;
            if (_cursor.isNull(_cursorIndexOfBookName)) {
              _tmpBookName = null;
            } else {
              _tmpBookName = _cursor.getString(_cursorIndexOfBookName);
            }
            final String _tmpBookNameEn;
            if (_cursor.isNull(_cursorIndexOfBookNameEn)) {
              _tmpBookNameEn = null;
            } else {
              _tmpBookNameEn = _cursor.getString(_cursorIndexOfBookNameEn);
            }
            _item = new ChapterInfo(_tmpChapter,_tmpChapterName,_tmpChapterNameEn,_tmpBook,_tmpBookName,_tmpBookNameEn);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
