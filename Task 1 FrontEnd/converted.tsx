import React, { useState, useEffect, useCallback } from 'react';

interface User {
  name: string;
  email: string;
}

interface UserDataProps {
  userId: string;
}

const UserData: React.FC<UserDataProps> = ({ userId }) => {
  const [user, setUser] = useState<User | null>(null);
  const [seconds, setSeconds] = useState<number>(0);

  const fetchUserData = useCallback(async () => {
    try {
      const response = await fetch(`https://secret.url/user/${userId}`);
      if (!response.ok) throw new Error('Failed to fetch');
      const data: User = await response.json();
      setUser(data);
    } catch (error) {
      console.error('Error fetching user data:', error);
    }
  }, [userId]);

  // ComponentDidMount and timer setup
  useEffect(() => {
    const intervalId = setInterval(() => {
      setSeconds(prev => prev + 1);
    }, 1000);
    
    return () => clearInterval(intervalId);
  }, []);

  // Initial fetch and prop change handling
  useEffect(() => {
    fetchUserData();
  }, [fetchUserData]);

  return (
    <div>
      <h1>User Data Component</h1>
      {user ? (
        <div>
          <p>Name: {user.name}</p>
          <p>Email: {user.email}</p>
        </div>
      ) : (
        <p>Loading user data...</p>
      )}
      <p>Timer: {seconds} seconds</p>
    </div>
  );
};

export default UserData;