from rest_framework import serializers
from dj_rest_auth import serializers as authSerializer
from .models import Task, User

class UserSerializer(serializers.Serializer):
    first_name = serializers.CharField(max_length=255)
    last_name = serializers.CharField(max_length=255)
    email = serializers.EmailField()
    password = serializers.CharField(max_length=200)
        
    def save(self, request):
        print(self)
        user = User.objects.create_user(
            username=self.data['email'],
            email=self.data['email'],
            password=self.data['password']
        )
        user.first_name = self.data['first_name']
        user.last_name = self.data['last_name']
        user.save()
        
        return user

class CustomLoginSerializer(authSerializer.LoginSerializer):
    
    pass
        
class TaskSerializer(serializers.ModelSerializer):
    class Meta:
        model = Task
        fields = ('name', 'priority', 'status')
  
    
    