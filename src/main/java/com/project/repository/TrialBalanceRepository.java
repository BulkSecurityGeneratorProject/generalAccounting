package com.project.repository;

import com.project.domain.TrialBalance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the TrialBalance entity.
 */
// @SuppressWarnings("")
@Repository
public interface TrialBalanceRepository extends JpaRepository<TrialBalance, Long> {

    @Query(value = "SELECT c.from_account_id as 'id', sum(if(c.transaction_type='DEBIT',cast(c.amount as decimal),0)) as 'debit', sum(if(c.transaction_type='CREDIT',cast(c.amount as decimal),0)) as 'credit', c.from_account_id as 'trial_balance_id' FROM cash_book c WHERE c.jhi_date >= MAKEDATE(year(sysdate()), 1) group by c.from_account_id order by c.from_account_id", nativeQuery = true)
    Page<TrialBalance> findAllTrialBalances(Pageable pageable);
}
