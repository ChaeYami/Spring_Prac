package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/*
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // pk id
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 일때 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}


/*
// 동시성 고려 - ConcurrentHashMap, AtomicLong 사용 예시

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제를 고려하여 ConcurrentHashMap 사용
    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    // 원자적인 연산을 지원하는 AtomicLong 사용
    private static AtomicLong sequence = new AtomicLong(0L);

    @Override
    public Member save(Member member) {
        // 고유한 ID 생성을 위해 AtomicLong 사용
        long nextId = sequence.incrementAndGet();
        member.setId(nextId);
        // 동시성 문제를 고려하여 ConcurrentHashMap 사용
        store.put(nextId, member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 동시성 문제를 고려하여 ConcurrentHashMap에서 데이터 가져오기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 동시성 문제를 고려하여 ConcurrentHashMap의 values에서 데이터 조회
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
*/