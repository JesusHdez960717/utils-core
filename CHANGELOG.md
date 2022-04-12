* 5.1.0.RELEASE.20220411: Removed repo folder, from now on, use the spring data for db management and h2 db for simple DB schemas like in memory.

* 5.0.0.SNAPSHOT.20220327: Complete review of the plugin. Updated focussed mainly on server side/microservices architecture.
    * **JACKSON**:
        * **IMPROVEMENT** :raised_hands: : Extract Jackson folder, removed converter service.
    * **REPO**:
        * **IMPROVEMENT** :raised_hands: : Migrated in memory external repos.
        * **IMPROVEMENT** :raised_hands: : Migrated `JACKSONReadWriteExternalRepo` external repo.
        * **IMPROVEMENT** :raised_hands: : Migrated `JACKSONReadWriteExternalRepo` external repo.
        * **IMPROVEMENT** :raised_hands: : Migrated JPA external repo.
    * **UTILS**:
        * **IMPROVEMENT** :-1: : Cleaned `utils`.
