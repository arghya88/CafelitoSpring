package cafelito;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import cafelito.model.Address;
import cafelito.model.CoffeeShop;
import cafelito.repo.CoffeeRepository;

@Component
public class StoreInitializer {

	@Autowired
	public StoreInitializer(CoffeeRepository repository) throws Exception {

		if (repository.count() != 0) {
			return;
		}

		List<CoffeeShop> shops = readStores();
		repository.save(shops);
	}

	/**
	 * Reads a file {@code starbucks.csv} from the class path and parses it into {@link Store} instances about to
	 * persisted.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<CoffeeShop> readStores() throws Exception {

		ClassPathResource resource = new ClassPathResource("starbucks.csv");
		Scanner scanner = new Scanner(resource.getInputStream());
		String line = scanner.nextLine();
		scanner.close();

		FlatFileItemReader<CoffeeShop> itemReader = new FlatFileItemReader<CoffeeShop>();
		itemReader.setResource(resource);

		// DelimitedLineTokenizer defaults to comma as its delimiter
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(line.split(","));
		tokenizer.setStrict(false);

		DefaultLineMapper<CoffeeShop> lineMapper = new DefaultLineMapper<CoffeeShop>();
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(StoreFieldSetMapper.INSTANCE);
		itemReader.setLineMapper(lineMapper);
		itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
		itemReader.setLinesToSkip(1);
		itemReader.open(new ExecutionContext());

		List<CoffeeShop> shops = new ArrayList<>();
		CoffeeShop shop = null;

		do {

			shop = itemReader.read();

			if (shop != null) {
				shops.add(shop);
			}

		} while (shop != null);

		return shops;
	}

	private static enum StoreFieldSetMapper implements FieldSetMapper<CoffeeShop> {

		INSTANCE;

		/* 
		 * (non-Javadoc)
		 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet(org.springframework.batch.item.file.transform.FieldSet)
		 */
		@Override
		public CoffeeShop mapFieldSet(FieldSet fields) throws BindException {

			Point location = new Point(fields.readDouble("Longitude"), fields.readDouble("Latitude"));
			Address address = new Address(fields.readString("Street Combined"), fields.readString("City"),
					fields.readString("Postal Code"), location);

			return new CoffeeShop(fields.readString("Name"), address);
		}
	}
}
