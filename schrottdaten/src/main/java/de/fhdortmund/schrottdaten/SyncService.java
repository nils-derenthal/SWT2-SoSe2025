package de.fhdortmund.schrottdaten;

import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.Immobilie;
import de.fhdortmund.schrottdaten.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncService {
    private final MQTTPublisher publisher;
    private final EigentuemerRepo eigentuemerRepo;
    private final ImmobilienRepo immobilienRepo;

    private final ResourceLoader resourceLoader;

    /**
     * Automatically executed after the application starts.
     * <p>
     * Loads all {@code Eigentuemer} and {@code Immobilie} entries from the databases
     * and passes them to the {@link MQTTPublisher} for publishing.
     */
    @PostConstruct
    private void syncData() throws IOException {
        log.info("Starting Data sync...");
        var eigentuemer = eigentuemerRepo.findAll();
        var immobilien = getAllImmobilien();

        eigentuemer.forEach(publisher::publishMessage);
        immobilien.forEach(publisher::publishMessage);

        log.info("Sync finished");
    }

    private List<Immobilie> getAllImmobilien() throws IOException {
        var immobilien = immobilienRepo.findAll();

        var classloader = resourceLoader.getClassLoader();

        var resolver = new PathMatchingResourcePatternResolver(classloader);
        var resources = resolver.getResources("classpath:houses/*");

        immobilien.forEach(im -> {
            if (im.getBild() == null) {
                try {
                    im.setBild(getRandom(resources));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(new String(im.getBild()));
        });
        return immobilienRepo.saveAll(immobilien);
    }

    private byte[] getRandom (Resource[] resources) throws IOException {
        int rand  = (int) (Math.random() * resources.length);
        return resources[rand].getContentAsByteArray();
    }
}
