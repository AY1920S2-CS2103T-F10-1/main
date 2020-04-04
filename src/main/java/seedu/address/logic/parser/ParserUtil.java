package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.field.Address;
import seedu.address.model.item.field.Description;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    // TODO: BEAUTIFY THE EXCEPTION MESSAGE
    /**
     * Parses a {@code String level} into a {@code Level}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Level parseLevel(String level) throws ParseException {
        requireNonNull(level);
        String trimmedLevel = level.trim();
        for (Level value : Level.values()) {
            if (value.toString().equalsIgnoreCase(trimmedLevel)) {
                return value;
            }
        }
        throw new ParseException("Level of proficiency can only be one of these three types: basic, intermediate, "
                + "advanced.");
    }

    /**
     *
     * Parses a {@code String website} into a {@code Website}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Website parseWebsite(String website) throws ParseException {
        requireNonNull(website);
        String trimmedWebsite = website.trim();
        if (!Website.isValidWebsite(trimmedWebsite)) {
            throw new ParseException(Website.MESSAGE_CONSTRAINTS);
        }
        return new Website(trimmedWebsite);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag) || tag.length() == 0) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses an {@code String itemType} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static String parseItemType(String itemType) throws ParseException {
        requireNonNull(itemType);
        String trimmedItemType = itemType.trim();
        if (!Item.isValidItemType(trimmedItemType)) {
            throw new ParseException("Not a valid item type!");
        }
        return trimmedItemType;
    }

    /**
     * Parses a {@code String time} in MM-YYYY format into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedTime);
    }

    /**
     * Parses the Item Indices to give the required optional
     */
    public static Optional<List<Integer>> parseReditItemIndices(String indices) throws ParseException {
        if (indices == null) {
            return Optional.empty();
        } else if (indices.equals("")) {
            // Empty string will return an InvokationTargetException in the streams
            // TODO: Investigate how this can be combined with the else block
            return Optional.of(new ArrayList<>());
        } else {
            List<Integer> mappedIndices = Arrays.stream(indices.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            return Optional.of(mappedIndices);
        }
    }

    /**
     * Parses a {@code String displayFilePath} into a {@code DisplayPicture}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static DisplayPicture parseDisplayPicture(String displayFilePath) throws ParseException {
        requireNonNull(displayFilePath);
        String trimmedDisplayFilePath = displayFilePath.trim();
        if (DisplayPicture.isValidDisplayPicture(trimmedDisplayFilePath)) {
            return new DisplayPicture(trimmedDisplayFilePath);
        }
        throw new ParseException(DisplayPicture.MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String github} into a {@code Github}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code github} is invalid.
     */
    public static Github parseGithub(String github) throws ParseException {
        requireNonNull(github);
        String trimmedGithub = github.trim();
        if (Github.isValidGithub(trimmedGithub)) {
            return new Github(trimmedGithub);
        }
        throw new ParseException(Github.MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String university} into a {@code String university}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code github} is invalid.
     */
    public static String parseUniversity(String university) throws ParseException {
        requireNonNull(university);
        String trimmedUniversity = university.trim();
        if (Verifier.isValidUniversity(trimmedUniversity)) {
            return trimmedUniversity;
        }
        throw new ParseException(Verifier.UNIVERSITY_MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String major} into a {@code String major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static String parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (Verifier.isValidMajor(trimmedMajor)) {
            return trimmedMajor;
        }
        throw new ParseException(Verifier.MAJOR_MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String cap} into a {@code Double cap}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cap} is invalid.
     */
    public static Double parseCap(String cap) throws ParseException {
        requireNonNull(cap);
        String trimmedCap = cap.trim();
        if (Verifier.isValidCap(trimmedCap)) {
            double userCap = Double.valueOf(trimmedCap);
            return Math.round(userCap * 100.0) / 100.0;
        }
        throw new ParseException(Verifier.CAP_MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static String parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return trimmedDescription;
    }

    /**
     * Parses a {@code String role} into a {@code String role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static String parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (Verifier.isValidRole(trimmedRole)) {
            return trimmedRole;
        }
        throw new ParseException(Verifier.ROLE_MESSAGE_CONSTRAINTS);
    }
}
