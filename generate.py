import os
import yaml
import random
import string

def generate_random_string(length):
    """Generate a random string of given length."""
    return ''.join(random.choices(string.ascii_lowercase + string.digits, k=length))

def create_yaml_file(file_name, num_key_value_pairs):
    """Create a YAML file with random key-value pairs."""
    data = {}
    for _ in range(num_key_value_pairs):
        key = generate_random_string(5)
        value = generate_random_string(10)
        data[key] = value
    # Add common key 'security-pillar' with a unique value for each file
    data['security-pillar'] = generate_random_string(8)

    with open(file_name, 'w') as file:
        yaml.dump(data, file)

def main():
    # Create 1500 YAML files
    num_files = 1500
    num_key_value_pairs = 10
    current_directory = os.getcwd()

    for i in range(1, num_files + 1):
        file_name = f"file_{i}.yml"
        file_path = os.path.join(current_directory, file_name)
        create_yaml_file(file_path, num_key_value_pairs)

if __name__ == "__main__":
    main()