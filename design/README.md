## Non-Functional Requirements
- Highly scalable
- Availability over consistency

## Notes
- Object based storage
- Commodity hardware
- Horizontal Scaling (Partitioning/Sharding)
- Read heavy system
- Stores Blob (Prefer localized storage instead of random on disks)
- Hash range partition
- Use of streams (if file is large)
- Consistent hashing
- We will use indexing since we can bear penality on writes instead of reads.

### No-SQL
- Unstructured data
- Schemaless
- Dont need ACID properties
- Dont need Isolation since data is immutable
- No relation
- No range query to be performed
- Each object is simple, self-contained repository that includes the data, metadata.
- Replication: gonna use eventual consistency.
- Single-Leader Replication since write are less.