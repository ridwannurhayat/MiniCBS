package id.ridwan.minicbs.service;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.domain.account.LoanAccountMapper;
import id.ridwan.minicbs.loan.account.LoanAccount;
import id.ridwan.minicbs.loan.account.LoanAccountRepository;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanAccountServiceTest {

    @InjectMocks
    LoanAccountService service;

    @Mock
    LoanAccountRepository repository;

    @Mock
    LoanAccountMapper mapper;

    @Test
    void testCreate() {
        LoanAccountDto dto = new LoanAccountDto(null, null, null, null, null, null);
        LoanAccount entity = new LoanAccount();
        entity.setId(UUID.randomUUID());
        
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.persist(entity)).thenReturn(Uni.createFrom().item(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        Uni<LoanAccountDto> result = service.create(dto);

        UniAssertSubscriber<LoanAccountDto> subscriber = result.subscribe().withSubscriber(UniAssertSubscriber.create());
        subscriber.assertCompleted().assertItem(dto);
        
        verify(repository).persist(entity);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        LoanAccount entity = new LoanAccount();
        entity.setId(id);
        LoanAccountDto dto = new LoanAccountDto(id, null, null, null, null, null);

        when(repository.findById(id)).thenReturn(Uni.createFrom().item(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        Uni<LoanAccountDto> result = service.findById(id);

        UniAssertSubscriber<LoanAccountDto> subscriber = result.subscribe().withSubscriber(UniAssertSubscriber.create());
        subscriber.assertCompleted().assertItem(dto);
        
        verify(repository).findById(id);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        LoanAccount entity = new LoanAccount();
        entity.setId(id);

        when(repository.findById(id)).thenReturn(Uni.createFrom().item(entity));

        Uni<Void> result = service.delete(id);

        UniAssertSubscriber<Void> subscriber = result.subscribe().withSubscriber(UniAssertSubscriber.create());
        subscriber.assertCompleted();

        verify(repository).findById(id);
    }
}
