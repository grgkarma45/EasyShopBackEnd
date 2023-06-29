package org.yearup.data.mysql;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    public Category getById(int categoryId) {
        String query = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Category create(Category category)
    {
        String sql = "INSERT INTO categories(name, description) " +
                " VALUES (?, ?);";

        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    // Retrieve the auto-incremented ID
                    int categoryId = generatedKeys.getInt(1);

                    // get the newly inserted category
                    category.setCategoryId(categoryId);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return category;
    }
    @Override
    public void update(int categoryId, Category category) {
        String query = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int categoryId) {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Category mapRow(ResultSet resultSet) throws SQLException {
        int categoryId = resultSet.getInt("category_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description);
        return category;
    }
}