package com.benoitmanhes.server.firestore

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.remotedatasource.AppControlRemoteDataSource
import com.benoitmanhes.domain.model.AppControl
import com.benoitmanhes.server.firestore.model.FSAppControl
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AppControlRemoteDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
) : FSRemoteDataSource<AppControl, FSAppControl>(firestore), AppControlRemoteDataSource {

    override val collectionRef: String = "appControl"
    override fun DocumentSnapshot.getFsClass(): Class<FSAppControl> = FSAppControl::class.java

    override suspend fun fetchAppControl(): AppControl? =
        getFSObject("appControlId")

    override fun AppControl.parseToFSModel(): FSAppControl {
        throw CTRemoteError.UnexpectedResult("Not suppose to push app control")
    }
}
