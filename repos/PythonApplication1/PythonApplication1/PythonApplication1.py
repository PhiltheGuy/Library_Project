import socket
import struct

def initiate_countdown(sock, countdown_value):
    if not (5 <= countdown_value <= 100):
        print("Countdown value must be between 5 and 100.")
        return

    message_code = 0x01  # COUNTDOWN_INITIATE
    payload = struct.pack('!I', countdown_value)  # Pack the countdown value
    message = struct.pack('!B', message_code) + payload  # Combine message code and payload

    print(f"Sending message: {message}")
    sock.sendall(message)

    response = sock.recv(1024)
    print(f"Raw response received: {response}")

    if len(response) < 5:
        print("Invalid response length.")
        return

    response_code = response[0]
    countdown = struct.unpack('!I', response[1:5])[0]

    if response_code == 0x00:
        print(f"Countdown initiated successfully: {countdown}")
        return countdown
    elif response_code == 0x02:
        print("Error: Malformed Message.")
    elif response_code == 0x05:
        print("Error: Communication Shutdown.")
    else:
        print(f"Failed to initiate countdown, response code: {response_code}")

def decrement_countdown(sock, current_value):
    if current_value < 0:
        print("Countdown has already reached zero.")
        return None

    message_code = 0x02  # COUNTDOWN_DECREMENT
    payload = struct.pack('!I', current_value)  # Pack the current countdown value
    message = struct.pack('!B', message_code) + payload  # Combine message code and payload

    print(f"Sending message: {message}")
    sock.sendall(message)

    response = sock.recv(1024)
    print(f"Raw response received: {response}")

    if len(response) < 5:
        print("Invalid response length.")
        return None

    response_code = response[0]
    countdown = struct.unpack('!I', response[1:5])[0]

    if response_code == 0x00:
        print(f"Countdown decremented successfully: {countdown}")
        return countdown
    elif response_code == 0x02:
        print("Error: Malformed Message.")
    elif response_code == 0x05:
        print("Error: Communication Shutdown.")
    else:
        print(f"Failed to decrement countdown, response code: {response_code}")
        return None

def fire_laser(sock):
    message_code = 0x03  # FIRE_LASER
    message = struct.pack('!B', message_code)  # Just the message code

    print(f"Sending message: {message}")
    sock.sendall(message)

    response = sock.recv(1024)
    print(f"Raw response received: {response}")

    response_code = response[0]
    if response_code == 0x00:
        print("Firing sequence initiated.")
    elif response_code == 0x02:
        print("Error: Malformed Message.")
    elif response_code == 0x05:
        print("Error: Communication Shutdown.")
    else:
        print(f"Failed to fire laser, response code: {response_code}")

def confirm_fire(sock):
    message_code = 0x04  # FIRE_LASER_CONFIRMATION
    message = struct.pack('!B', message_code)  # Just the message code

    print(f"Sending message: {message}")
    sock.sendall(message)

    response = sock.recv(1024)
    print(f"Raw response received: {response}")

    if len(response) < 42:
        print("Invalid response length.")
        return

    response_code = response[0]
    confirmation_message = response[1:41].decode('utf-8').strip()  # Decode and trim

    if response_code == 0x00:
        print(f"Fire confirmation: {confirmation_message}")
    elif response_code == 0x02:
        print("Error: Malformed Message.")
    elif response_code == 0x05:
        print("Error: Communication Shutdown.")
    else:
        print(f"Failed to confirm fire, response code: {response_code}")

def shutdown_communication(sock):
    message_code = 0x05  # COMMUNICATION_SHUTDOWN
    message = struct.pack('!B', message_code)  # Just the message code

    print(f"Sending message: {message}")
    sock.sendall(message)

    response = sock.recv(1024)
    print(f"Raw response received: {response}")

    response_code = response[0]
    if response_code == 0x00:
        print("Communication shutdown successfully.")
    elif response_code == 0x02:
        print("Error: Malformed Message.")
    elif response_code == 0x05:
        print("Error: Communication Shutdown.")
    else:
        print(f"Failed to shutdown communication, response code: {response_code}")

if __name__ == "__main__":
    countdown_value = 20  # Example countdown value
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect(('localhost', 1977))  # Replace with your server address and port

        initiate_countdown(sock, countdown_value)

        current_countdown = countdown_value
        while current_countdown > 0:
            current_countdown = decrement_countdown(sock, current_countdown - 1)
            if current_countdown is None:
                break

        fire_laser(sock)
        confirm_fire(sock)
        shutdown_communication(sock)
