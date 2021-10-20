package com.mrtienthinh.nguyentienthinh;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeedback(FeedbackEntity feedback);

    @Update
    void updateFeedback(FeedbackEntity feedback);

    @Delete
    void deleteFeedback(FeedbackEntity feedback);

    @Query("select * from Feedback")
    List<FeedbackEntity> getAllFeedback();

    @Query("select * from Feedback where id = :id")
    FeedbackEntity getFeedback(int id);

    @Query("Delete from Feedback")
    void deleteAll();
}
