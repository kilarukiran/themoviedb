package org.github.kilarukirankumar.model.movies;

import java.util.List;

public class MoviesBo {

    private int page;
    private List<MovieBo> movies = null;
    private int totalResults;
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieBo> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieBo> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
