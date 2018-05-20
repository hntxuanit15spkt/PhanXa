/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vn.com.canhtoan.Database.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;


import java.util.Calendar;
import java.util.Date;

import vn.com.canhtoan.Database.AppDatabase;
import vn.com.canhtoan.Database.Entity.CauDocEntity;
import vn.com.canhtoan.Database.Entity.CauPhanXaEntity;
import vn.com.canhtoan.Database.Entity.MucDoPhanXaEntity;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static CauPhanXaEntity addCauPhanXa(final AppDatabase db, final String question,
                                                final String answer, final double time,final int id_mucdo) {
        CauPhanXaEntity cauPhanXaEntity = new CauPhanXaEntity();
        cauPhanXaEntity.question = question;
        cauPhanXaEntity.answer = answer;
        cauPhanXaEntity.time = time;
        cauPhanXaEntity.id_mucdo = id_mucdo;
        db.cauphanxaDao().insert(cauPhanXaEntity);
        return cauPhanXaEntity;
    }

    private static MucDoPhanXaEntity addMucDo(final AppDatabase db, final String name) {
        MucDoPhanXaEntity mucDoEntity = new MucDoPhanXaEntity();
        mucDoEntity.name = name;
        db.mucDoPhanXaDAO().insert(mucDoEntity);
        return mucDoEntity;
    }

    private static CauDocEntity addCauDoc(final AppDatabase db, final String sentence,
                                          final int sound, final int time) {
        CauDocEntity cauDocEntity = new CauDocEntity();
        cauDocEntity.sentence = sentence;
        cauDocEntity.sound = sound;
        cauDocEntity.time = time;
        db.cauDocDAO().insert(cauDocEntity);
        return cauDocEntity;
    }

    private static void populateWithTestData(@NonNull final AppDatabase db) {
        addCauDoc(db,"Are they the same?",0, 2);
        addCauDoc(db,"Are you afraid?",0, 2);
        addCauDoc(db,"Are you going to attend their wedding?",0, 4);
        addCauDoc(db,"Are you OK?",0, 2);
        addCauDoc(db,"Are you sick?",0, 2);
        addCauDoc(db,"Behind the bank",0, 2);
        addCauDoc(db,"Can I borrow some money?",0, 3);
        addMucDo(db,"De");
        addMucDo(db,"Trung binh");
        addMucDo(db,"Kho");
        addCauPhanXa(db, "How old are you?", "I'm 21 years old", 1, 1);
        /*User user2 = addUser(db, "2", "Mike", "Seaver", 12);
        addUser(db, "3", "Carol", "Seaver", 15);

        Book book1 = addBook(db, "1", "Dune");
        Book book2 = addBook(db, "2", "1984");
        Book book3 = addBook(db, "3", "The War of the Worlds");
        Book book4 = addBook(db, "4", "Brave New World");
        addBook(db, "5", "Foundation");*/
        /*try {
            Date today = getTodayPlusDays(0);
            Date yesterday = getTodayPlusDays(-1);
            Date twoDaysAgo = getTodayPlusDays(-2);
            Date lastWeek = getTodayPlusDays(-7);
            Date twoWeeksAgo = getTodayPlusDays(-14);

            addLoan(db, "1", user1, book1, twoWeeksAgo, lastWeek);
            Thread.sleep(DELAY_MILLIS);
            addLoan(db, "2", user2, book1, lastWeek, yesterday);
            Thread.sleep(DELAY_MILLIS);
            addLoan(db, "3", user2, book2, lastWeek, today);
            Thread.sleep(DELAY_MILLIS);
            addLoan(db, "4", user2, book3, lastWeek, twoDaysAgo);
            Thread.sleep(DELAY_MILLIS);
            addLoan(db, "5", user2, book4, lastWeek, today);
            Log.d("DB", "Added loans");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
