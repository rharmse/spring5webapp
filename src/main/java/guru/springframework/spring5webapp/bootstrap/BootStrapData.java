package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
      PublisherRepository publisherRepository) {

    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Author eric = new Author("Eric", "Evans");
    authorRepository.save(eric);
    Book ddd = new Book("Domain Driven Design", "ABC-123-ABC-123");
    Book dp = new Book("Design Patterns", "QWE-123-QWE-123");
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);
    bookRepository.saveAll(eric.getBooks());
    eric.getBooks().add(dp);
    dp.getAuthors().add(eric);
    bookRepository.saveAll(eric.getBooks());

    Author rod = new Author("Rod", "Johnson");
    authorRepository.save(rod);

    Book spring = new Book("Spring In Action", "QWE-312-REE-123");
    rod.getBooks().add(spring);
    bookRepository.saveAll(rod.getBooks());

    spring.getAuthors().add(rod);

    Publisher oreily = new Publisher("Oreilly");
    oreily.getBooks().add(ddd);
    oreily.getBooks().add(spring);
    publisherRepository.save(oreily);

    System.out.println("Bootstrap Started");
    System.out.printf("Number of Books: %s\n", bookRepository.count());
    System.out.printf("Number of Authors: %s\n", authorRepository.count());
    System.out.printf("Number of Publishers: %s\n", publisherRepository.count());
  }
}
