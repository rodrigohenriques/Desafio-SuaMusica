package br.com.suamusica.domain.interactor;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;
import br.com.suamusica.domain.repository.AlbumsRepository;

public class ListTrendingAlbumsUseCaseImpl  extends AbstractUseCase<ListTrendingAlbumsUseCaseImpl.Input, List<Album>> implements ListTrendingAlbumsUseCase {

    private AlbumsRepository mAlbumsRepository;

    @Inject
    public ListTrendingAlbumsUseCaseImpl(UiThreadExecutor mUiThreadExecutor, AlbumsRepository albumsRepository) {
        super(mUiThreadExecutor);

        this.mAlbumsRepository = albumsRepository;
    }

    @Override
    public void execute(int page, QueryType queryType, Callback<List<Album>> callback) {
        executeAsync(new Input(page, queryType), callback);
    }

    @Override
    protected List<Album> executeOnBackground(Input input) throws IOException {
        return mAlbumsRepository.listTrendingMusic(input.page, input.queryType);
    }

    class Input {
        int page;
        QueryType queryType;

        public Input(int page, QueryType queryType) {
            this.page = page;
            this.queryType = queryType;
        }
    }
}
