package commands;

import org.crsh.cli.Argument;
import org.crsh.cli.Command;
import org.crsh.cli.Required;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.crsh.command.InvocationContext;
import org.opencog.atomspace.Atom;
import org.opencog.atomspace.GenericHandle;
import org.opencog.atomspace.GraphBackingStore;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

/**
 * Created by ceefour on 6/28/15.
 */
@Usage("Atom operations")
public class atom extends BaseCommand {

    @Usage("Get a single atom by handle UUID")
    @Command
    public void get(@Usage("Atom handle UUID") @Required @Argument String uuidStr,
                     InvocationContext context) {
        final ConfigurableListableBeanFactory appCtx = (ConfigurableListableBeanFactory) context.getAttributes().get("spring.beanfactory");
        final GraphBackingStore backingStore = appCtx.getBean("zmqGraphBackingStore", GraphBackingStore.class);
        final GenericHandle handle = new GenericHandle(Long.valueOf(uuidStr));
        final Optional<Atom> atom = backingStore.getAtom(handle);
        atom.ifPresent(it -> System.out.println(it));
    }
}