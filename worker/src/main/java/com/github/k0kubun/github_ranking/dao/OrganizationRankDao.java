package com.github.k0kubun.github_ranking.dao;

import com.github.k0kubun.github_ranking.model.OrganizationRank;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.BatchChunkSize;

public interface OrganizationRankDao
{
    @SqlUpdate("delete from organization_ranks where stargazers_count between :min and :max")
    long deleteBetween(@Bind("min") int min, @Bind("max") int max);

    @SqlBatch("insert into organization_ranks (stargazers_count, rank, created_at, updated_at) " +
              "values (:stargazersCount, :rank, current_timestamp(), current_timestamp())")
    @BatchChunkSize(5000)
    void bulkInsert(@BindBean List<OrganizationRank> orgRanks);
}