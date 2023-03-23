package com.benoitmanhes.server.firestore.model

import com.benoitmanhes.domain.model.Cache

interface FSCache<C : Cache> : FirestoreModel<C>
