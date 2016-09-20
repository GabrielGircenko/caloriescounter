package com.gircenko.gabriel.calcounter.repos.firebase.database;

import java.util.Map;

/**
 * Created by Gabriel Gircenko on 21-Sep-16.
 */
public interface OnGetUserListListener {
    void onChildAdded(String userId, String name);
}
