package io.microraft.store.sqlite;

import io.microraft.RaftEndpoint;
import io.microraft.model.log.LogEntry;
import io.microraft.model.log.RaftGroupMembersView;
import io.microraft.model.log.SnapshotChunk;

import javax.annotation.Nonnull;

/**
 * Similarly to the {@link io.microraft.model.RaftModelFactory}, users of the SQLite implementation must provide
 * methods for converting a few of their types into binary data for persistence. This logic is expected to be
 * relatively straightforward for the implementer, since similar logic will exist within the
 * {@link io.microraft.transport.Transport}. It should be noted that serialization performed here may need to be
 * deserialized for an indefinite period and so evolution of any relevant types should be considered by the
 * implementer.
 */
public interface StoreModelSerializer {
    Serializer<RaftGroupMembersView> raftGroupMembersViewSerializer();

    Serializer<RaftEndpoint> raftEndpointSerializer();

    Serializer<LogEntry> logEntrySerializer();

    Serializer<SnapshotChunk> snapshotChunkSerializer();

    interface Serializer<T> {
        @Nonnull
        byte[] serialize(@Nonnull T element);

        @Nonnull
        T deserialize(@Nonnull byte[] element);
    }
}
